package fi.solita.kivilahtio.repository;

import java.util.List;

import fi.solita.kivilahtio.model.Todo;
import fi.solita.kivilahtio.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
    List<Todo> findByTitleAndDescription(String title, String description);

    List<Todo> findDistinctTodoByTitleOrDescription(String title,
                                                    String description);

    List<Todo> findByTitleIgnoreCase(String title);

    List<Todo> findByTitleOrderByIdDesc(String title);

    List<Todo> findByIsDoneTrue();
}
