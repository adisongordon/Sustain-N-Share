package eco.sustainnshare.webapp.repository;

import eco.sustainnshare.webapp.entity.Items;
import eco.sustainnshare.webapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer>, JpaSpecificationExecutor<Items> {
    List<Items> findAllByDonor(Users donor);
    List<Items> findAllByReceiver(Users receiver);
}
