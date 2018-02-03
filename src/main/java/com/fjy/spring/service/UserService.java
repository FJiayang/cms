package com.fjy.spring.service;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.repository.TbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private TbUserRepository tbUserRepository;


    public boolean doLoginService(String name,String password){
        TbUser user = (TbUser)tbUserRepository.findByColname(name).get();
        if (user!=null){
            if (password.equals(user.getColpassword())){
                return true;
            }else {
                throw new UserException(ResultEnum.WRONGPASS);
            }
        }else {
            throw new UserException(ResultEnum.USER_NOTEXIST);
        }
    }

    public boolean doRegisterService(TbUser tbUser){

        TbUser user = tbUserRepository.save(tbUser);
        if (user!=null){
            throw new UserException(ResultEnum.SUCCESS);
        }
        return false;
    }
}
