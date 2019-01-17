package hello.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import hello.models.Owner;
import hello.models.Pet;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Query implements GraphQLQueryResolver {

    public List<Pet> pets() {
        List<Pet> pets = new ArrayList<>();
        for(Map<String, Object> m:load()) {
            Pet aPet = new Pet();
            aPet.setName(m.get("name").toString());
            aPet.setBirthDate(m.get("birth_date").toString());
            Owner owner = new Owner();
            owner.setFirstName(m.get("first_name").toString());
            owner.setLastName(m.get("last_name").toString());
            aPet.setOwner(owner);
            pets.add(aPet);
        }
        return pets;
    }


    private List<Map<String, Object>> load(){
        DBI dbi = getDB();
        Handle handle = dbi.open();
        String sql = "SELECT * from petclinic.pets p, owners o WHERE p.owner_id = o.id";
        org.skife.jdbi.v2.Query<Map<String, Object>> q = handle.createQuery(sql);
        List<Map<String, Object>> result = q.list();
        dbi.close(handle);
        return result;
    }

    private  DBI getDB(){
        String url = "jdbc:mysql://localhost:3306/petclinic";
        return new DBI(url, "root", "petclinic");
    }

}