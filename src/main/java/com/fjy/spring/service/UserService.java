package com.fjy.spring.service;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.domain.TbUserque;
import com.fjy.spring.domain.VUserinfo;
import com.fjy.spring.domain.VUserque;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.repository.TbUserRepository;
import com.fjy.spring.repository.TbUserqueRepository;
import com.fjy.spring.repository.VUserinfoRepository;
import com.fjy.spring.repository.VUserqueRepository;
import com.fjy.spring.untils.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private TbUserRepository tbUserRepository;

    @Autowired
    private TbUserqueRepository userqueRepository;

    @Autowired
    private VUserqueRepository vUserqueRepository;

    @Autowired
    private VUserinfoRepository vUserinfoRepository;


    public TbUser doLoginService(String name, String password) {
        //TbUser user = (TbUser)tbUserRepository.findByColname(name).get();
        Optional<TbUser> tbUser = tbUserRepository.findByColname(name);
        TbUser user = new TbUser();
        if (tbUser.isPresent()) {
            user = (TbUser) tbUser.get();
        } else {
            throw new UserException(ResultEnum.EMPTY_DATA);
        }

        if (user != null) {
            if (password.equals(user.getColpassword())) {
                return user;
            } else {
                throw new UserException(ResultEnum.WRONGPASS);
            }
        } else {
            throw new UserException(ResultEnum.USER_NOTEXIST);
        }
    }

    public boolean doRegisterService(TbUser tbUser) {

        TbUser user = tbUserRepository.save(tbUser);
        if (user != null) {
            //throw new UserException(ResultEnum.SUCCESS);
            return true;
        }
        return false;
    }

    public List<TbUser> findAllUser() {
        return tbUserRepository.findAll();
    }

    public VUserinfo findUserInfo(Integer coluserid) {
        return vUserinfoRepository.findById(coluserid).get();
    }

    public Optional<TbUser> findByColname(String name) {
        return tbUserRepository.findByColname(name);
    }

    public boolean addUserQue(TbUserque userque) {
        TbUserque tbUserque = userqueRepository.save(userque);
        if (tbUserque != null)
            return true;
        return false;
    }

    public Optional<VUserque> findUserQueByName(String name) {
        return vUserqueRepository.findByName(name);
    }

    @Transactional
    public boolean updateColpasswordByColname(String password, String name) throws Exception {
        return tbUserRepository.updateColpasswordByColname(new BigInteger(CodingUtil.encryptSHA(password.getBytes())).toString(32), name) > 0;
    }
}
