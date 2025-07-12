package com.ziko.specification;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import com.ziko.weather_app.model.Location;
import jakarta.persistence.criteria.*;

public class LocationSpecifications {
        public static Specification<Location> matchLocation(Location location) {
                return (Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(cb.equal(root.get("city"), location.getCity()));
                        predicates.add(cb.equal(root.get("country"), location.getCountry()));

                        predicates.add(location.getAdmin1() != null ? cb.equal(root.get("admin1"), location.getAdmin1())
                                        : cb.isNull(root.get("admin1")));
                        predicates.add(location.getAdmin2() != null ? cb.equal(root.get("admin2"), location.getAdmin2())
                                        : cb.isNull(root.get("admin2")));
                        predicates.add(location.getAdmin3() != null ? cb.equal(root.get("admin3"), location.getAdmin3())
                                        : cb.isNull(root.get("admin3")));
                        predicates.add(location.getAdmin4() != null ? cb.equal(root.get("admin4"), location.getAdmin4())
                                        : cb.isNull(root.get("admin4")));

                        return cb.and(predicates.toArray(new Predicate[0]));
                };
        }
}
