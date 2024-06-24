package edu.icet.pos.dao.custom.impl;

import org.hibernate.Session;

UserDaoImpl implements UserDao {

    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;

//        try {
//            String SQL = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?)";
//            CrudUtil.execute(
//                    SQL,
//                    entity.getId(),
//                    entity.getCustomerTitle(),
//                    entity.getCustomerName(),
//                    entity.getDob(),
//                    entity.getSalary(),
//                    entity.getAddress(),
//                    entity.getCity(),
//                    entity.getProvince(),
//                    entity.getPostalCode()
//            );
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}