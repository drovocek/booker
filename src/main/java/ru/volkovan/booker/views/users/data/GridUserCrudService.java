package ru.volkovan.booker.views.users.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.volkovan.booker.general.data.AppCrudService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GridUserCrudService implements AppCrudService<GridUser, FilterUser> {

    private final UserRepository userRepository;

    @Override
    public List<GridUser> findByFilter(FilterUser filter) {
        System.out.println(filter);
        return this.userRepository.findAll();
    }

    @Override
    public void delete(GridUser deleted) {
        System.out.println(deleted);
        this.userRepository.delete(deleted);
    }

    @Override
    public GridUser save(GridUser persist) {
        return this.userRepository.save(persist);
    }
}
