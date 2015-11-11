package com.spontaneous.server.service;

import com.spontaneous.server.model.entity.User;
import com.spontaneous.server.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.stereotype.Service;

/**
 * Created by eidan on 5/23/15.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository mUserDao;

    @Autowired
    private FacebookService mFacebookService;

    public User getUserById(long id) {
        return mUserDao.findOne(id);
    }

    public User login(String facebookUserId, String facebookToken) throws ServiceException {
        try {
            User user = mUserDao.findByFacebookUserId(facebookUserId);

            if(user == null) {
                user = new User();
                user.setFacebookUserId(facebookUserId);
                user.setFacebookToken(facebookToken);
            }

            mFacebookService.getUserDetails(user, facebookToken, facebookUserId);
            return mUserDao.save(user);
        } catch(ServiceException e) {
            e.printStackTrace();
            throw e;
        }
    }
}