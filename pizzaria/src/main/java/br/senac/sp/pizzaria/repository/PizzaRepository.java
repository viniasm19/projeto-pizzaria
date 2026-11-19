package br.senac.sp.pizzaria.repository;

import br.senac.sp.pizzaria.dao.InterfaceDao;
import br.senac.sp.pizzaria.model.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PizzaRepository implements InterfaceDao<Pizza> {

    @Override
    public void inserir(Pizza objeto) {
        try (EntityManager manager = JpaUtil.getEntityManager()) {
            manager.getTransaction().begin();
            manager.persist(objeto);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Pizza objeto) {
        try (EntityManager manager = JpaUtil.getEntityManager()) {
            manager.getTransaction().begin();
            manager.merge(objeto);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Long id) {
        try (EntityManager manager = JpaUtil.getEntityManager()) {
            Pizza pizza = manager.find(Pizza.class, id);
            manager.getTransaction().begin();
            manager.remove(pizza);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pizza> listar() {
        try (EntityManager manager = JpaUtil.getEntityManager()) {
            TypedQuery<Pizza> query =
                    manager.createQuery("select p from Pizza p order by p.sabor",Pizza.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pizza> listar(String ingredientes) {
        try (EntityManager manager = JpaUtil.getEntityManager()) {
            TypedQuery<Pizza> query =
                    manager.createQuery("select p from Pizza p where p.ingredientes like :ingrediente order by p.sabor",Pizza.class);
            query.setParameter("ingrediente","%"+ingredientes+"%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
