package libreria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
        private static EntityManagerFactory emf;
        private static EntityManager em;
        public static EntityManager getEntityManager() {
            if (em == null) {
                emf = Persistence.createEntityManagerFactory("unidadEjemplo");
            }
            em = emf.createEntityManager();
            return em;
        }

        public static void closeEntityManager() {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
}
