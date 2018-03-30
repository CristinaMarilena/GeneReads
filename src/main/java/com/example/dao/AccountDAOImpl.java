package com.example.dao;

import com.example.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addAccount(Account Account) {
        getCurrentSession().save(Account);
    }

    public void updateAccount(Account account) {
        Account accountToUpdate = getAccount(account.getUserId());
        accountToUpdate.setUsername(account.getUsername());
        accountToUpdate.setPassword(account.getPassword());
        getCurrentSession().update(accountToUpdate);

    }

    public Account getAccount(int id) {
        Account Account = (Account) getCurrentSession().get(Account.class, id);
        return Account;
    }

    public void deleteAccount(int id) {
        Account Account = getAccount(id);
        if (Account != null)
            getCurrentSession().delete(Account);
    }

    public List<Account> getAccounts() {
        return getCurrentSession().createQuery("from Account").list();
    }

}