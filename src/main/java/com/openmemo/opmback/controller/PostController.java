package com.openmemo.opmback.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openmemo.opmback.entity.common.MessageBody;
import com.openmemo.opmback.entity.customer.CustomerDto;
import com.openmemo.opmback.entity.post.PostDto;
import com.openmemo.opmback.entity.post.PostResponce;
import com.openmemo.opmback.entity.post.delete.PostDeleteRequest;
import com.openmemo.opmback.entity.post.insert.PostInsertRequest;
import com.openmemo.opmback.entity.post.insert.PostInsertResponce;
import com.openmemo.opmback.entity.post.select.PostSelectResponce;
import com.openmemo.opmback.entity.post.select.PostSelectResponseBody;
import com.openmemo.opmback.entity.post.selectMine.PostSelectMineRequest;
import com.openmemo.opmback.entity.post.selectMine.PostSelectMineResponce;
import com.openmemo.opmback.entity.post.selectMine.PostSelectMineResponseBody;
import com.openmemo.opmback.entity.post.update.PostUpdateRequest;
import com.openmemo.opmback.service.CustomerService;
import com.openmemo.opmback.service.PostService;
import com.openmemo.opmback.util.ConstantUtil;

@RestController
@RequestMapping(path = "post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;
    @Autowired
    private CustomerService customerService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Object> selectMine(@RequestBody PostSelectMineRequest req, Authentication authentication) {
        try {
            List<MessageBody> messages = new ArrayList<MessageBody>();
            List<PostDto> resultPostList = new ArrayList<PostDto>();
            Integer customerId = getCurrentCustomerId(authentication);

            // ここにバリデーションやチェックがあればmessagesに加える
            if (messages.size() == 0) {
                resultPostList = postService.selectMine(customerId);
            }

            PostSelectMineResponce res = new PostSelectMineResponce();
            PostSelectMineResponseBody body = new PostSelectMineResponseBody();
            body.setPostList(resultPostList);
            res.setBody(body);

            if (messages.size() != 0) {
                return getResponseEntity(messages, res, HttpStatus.BAD_REQUEST);
            } else {
                return getResponseEntity(messages, res, HttpStatus.OK);
            }
        } catch (Exception e) {
            return responseError(e);
        }
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Object> select(@RequestBody PostSelectMineRequest req, @PathVariable Integer id, Authentication authentication) {
        try {
            // 前処理
            List<MessageBody> messages = new ArrayList<MessageBody>();
            Integer customerId = getCurrentCustomerId(authentication);

            // ここにバリデーションやチェックがあればmessagesに加える

            PostDto result = new PostDto();
            if (messages.size() == 0) {
                result = postService.select(id);
                if(result.getCustomerid() != customerId){
                    MessageBody message = new MessageBody();
                    message.setMessage(ConstantUtil.API_RESULT_ERROR_MESSAGE_DIFFERENT_USER);
                    messages.add(message);
                }
            }

            PostSelectResponce res = new PostSelectResponce();
            PostSelectResponseBody body = new PostSelectResponseBody();
            body.setPost(result);
            res.setBody(body);

            if (messages.size() != 0) {
                return getResponseEntity(messages, res, HttpStatus.BAD_REQUEST);
            } else {
                return getResponseEntity(messages, res, HttpStatus.OK);
            }
        } catch (Exception e) {
            return responseError(e);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody PostInsertRequest req, Authentication authentication) {
        try {
            // 前処理
            List<MessageBody> messages = new ArrayList<MessageBody>();
            Integer customerId = getCurrentCustomerId(authentication);

            // メイン処理
            int insertCount = 0;
            if (messages.size() == 0) {
                insertCount = postService.insert(req, customerId);
                
            }

            // 後処理
            PostInsertResponce res = new PostInsertResponce();
            res.setInsertCount(insertCount);
            return getResponseEntity(messages, res, HttpStatus.OK);
        } catch (Exception e) {
            // エラー処理
            return responseError(e);
        }
    }

    @CrossOrigin
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody PostUpdateRequest req, @PathVariable Integer id,
            Authentication authentication) {
        try {
            List<MessageBody> messages = new ArrayList<MessageBody>();
            int updateCount = 0;
            // ここにバリデーションやチェックがあればmessagesに加える
            if (messages.size() == 0) {
                updateCount = postService.update(id, req);
            }
            PostInsertResponce res = new PostInsertResponce();
            res.setInsertCount(updateCount);
            return getResponseEntity(messages, res, HttpStatus.OK);
        } catch (Exception e) {
            return responseError(e);
        }
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@RequestBody PostDeleteRequest req, @PathVariable Integer id) {
        try {
            List<MessageBody> messages = new ArrayList<MessageBody>();
            int deleteCount = 0;
            // ここにバリデーションやチェックがあればmessagesに加える
            if (messages.size() == 0) {
                deleteCount = postService.delete(id);
            }
            PostInsertResponce res = new PostInsertResponce();
            res.setInsertCount(deleteCount);
            return getResponseEntity(messages, res, HttpStatus.OK);
        } catch (Exception e) {
            return responseError(e);
        }
    }

    // Responseの共通処理
    protected ResponseEntity<Object> getResponseEntity(List<MessageBody> messages, PostResponce res,
            HttpStatus status) {
        if (messages.size() != 0) {
            res.setResult(ConstantUtil.API_RESULT_ERROR);
            res.setMessage(messages);
            return new ResponseEntity<Object>(res, new HttpHeaders(), status);
        } else {
            MessageBody messageBody = new MessageBody();
            res.setResult(ConstantUtil.API_RESULT_SUCCESS);
            messageBody.setMessage(ConstantUtil.API_RESULT_SUCCESS_MESSAGE);
            messages.add(messageBody);
            res.setMessage(messages);
            return new ResponseEntity<Object>(res, new HttpHeaders(), status);
        }
    }

    // errorをcatchした際の共通処理
    protected ResponseEntity<Object> responseError(Exception e) {
        LOGGER.error(ConstantUtil.API_RESULT_ERROR_MESSAGE, e);

        List<MessageBody> messages = new ArrayList<MessageBody>();
        MessageBody messageBody = new MessageBody();
        messageBody.setMessage(ConstantUtil.API_RESULT_ERROR_MESSAGE);
        messages.add(messageBody);

        return getResponseEntity(messages, new PostResponce(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ログイン中のCustomerのidを取得する。
    private Integer getCurrentCustomerId(Authentication authentication) {
        List<CustomerDto> customerList = new ArrayList<CustomerDto>();
        customerList = customerService.findByEmail(authentication.getName());

        Integer customerId;
        if (customerList.size() != 1) {
            throw new BadCredentialsException("No user registered with this details!");
        } else {
            customerId = customerList.get(0).getId();
        }
        return customerId;
    }
}
