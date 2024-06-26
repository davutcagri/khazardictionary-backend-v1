package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.FileAttachment;
import com.khazardictionary.backend.jpa.repository.FileAttachmentRepository;
import com.khazardictionary.backend.jpa.model.Post;
import com.khazardictionary.backend.jpa.repository.PostRepository;
import com.khazardictionary.backend.jpa.vm.PostSumbitVM;
import com.khazardictionary.backend.jpa.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author davut
 */
@Service
public class PostService {

    PostRepository postRepository;
    FileAttachmentRepository fileAttachmentRepository;
    FileService fileService;
    UserService userService;

    public PostService(PostRepository postRepository,
            UserService userService,
            FileAttachmentRepository fileAttachmentRepository,
            FileService fileService) {
        this.postRepository = postRepository;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.fileService = fileService;
        this.userService = userService;
    }

    public void save(PostSumbitVM postSumbitVM, User user) {
        Post post = new Post();
        post.setTitle(postSumbitVM.getTitle());
        post.setContent(postSumbitVM.getContent());
        post.setTimestamp(new Date());
        post.setCategory(postSumbitVM.getCategory());
        post.setUser(user);
        post.setCommentsLocked(false);
        postRepository.save(post);
        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(postSumbitVM.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setPost(post);
            fileAttachmentRepository.save(fileAttachment);
        }
    }

    public Post getPost(Long id) {
        return postRepository.getOne(id);
    }

    public Page<Post> getPosts(Pageable page) {
        return postRepository.findAll(page);
    }

    public Page<Post> getPostsByUsername(String username, Pageable page) {
        User inDB = userService.getByUsername(username);
        return postRepository.findByUser(inDB, page);
    }
    
    public Page<Post> getPostsByCategories(String category, Pageable page) {
        return postRepository.findByCategory(category, page);
    }

    public Page<Post> getOldPosts(long id, String username, Pageable page) {
        Specification<Post> specification = idLessThan(id);
        if (username != null) {
            User inDB = userService.getByUsername(username);
            specification = specification.and(userIs(inDB));
        }
        return postRepository.findAll(specification, page);
    }

    public long getNewPostsCount(long id, String username) {
        Specification<Post> specification = idGreaterThan(id);
        if (username != null) {
            User inDB = userService.getByUsername(username);
            specification = specification.and(userIs(inDB));
        }
        return postRepository.count(specification);
    }

    public List<Post> getNewPosts(long id, String username, Sort sort) {
        Specification<Post> specification = idGreaterThan(id);
        if (username != null) {
            User inDB = userService.getByUsername(username);
            specification = specification.and(userIs(inDB));
        }
        return postRepository.findAll(specification, sort);
    }

    public void delete(long id) {
        Post inDB = postRepository.getOne(id);
        if (inDB.getFileAttachment() != null) {
            String fileName = inDB.getFileAttachment().getName();
            fileService.deleteAttachmentFile(fileName);
        }
        postRepository.deleteById(id);
    }

    public Post updatePostCommentLock(Long id) {
        Post post = postRepository.getOne(id);
        if(!post.isCommentsLocked()) {
            post.setCommentsLocked(true);
        }
        else {
            post.setCommentsLocked(false);
        }
        return postRepository.save(post);
    }

////////////////////////////////////////////////////////////////////////////////

    //Old Posts
    Specification<Post> idLessThan(long id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("id"), id);
        };
    }

    //New Posts
    Specification<Post> idGreaterThan(long id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("id"), id);
        };
    }

    //User in db
    Specification<Post> userIs(User user) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user"), user);
        };
    }

}
