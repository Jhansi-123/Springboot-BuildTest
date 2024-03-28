package com.nagarro.java.mini.assignment2.service.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.nagarro.java.mini.assignment2.entity.UserResponse;

@Component
@Primary
public class AgeSortingStrategy implements UserSortingStrategy {
    @Override
    public void sort(List<UserResponse> users, String sortingType) {
        Comparator<UserResponse> ageComparator = Comparator.comparingInt(UserResponse::getAge);

        List<UserResponse> sortedUsers = users.stream()
                .sorted(ageComparator)
                .collect(Collectors.toList());

        users.clear();
        if (sortingType.equalsIgnoreCase("EVEN")) {
            users.addAll(filterEven(sortedUsers));
            users.addAll(filterOdd(sortedUsers));
        } else {
            users.addAll(filterEven(sortedUsers));
            users.addAll(filterOdd(sortedUsers));
        }
    }

    private List<UserResponse> filterEven(List<UserResponse> users) {
        return users.stream()
                .filter(user -> user.getAge() % 2 == 0)
                .collect(Collectors.toList());
    }

    private List<UserResponse> filterOdd(List<UserResponse> users) {
        return users.stream()
                .filter(user -> user.getAge() % 2 != 0)
                .collect(Collectors.toList());
    }
}
