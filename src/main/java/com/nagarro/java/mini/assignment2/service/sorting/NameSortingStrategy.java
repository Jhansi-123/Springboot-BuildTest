package com.nagarro.java.mini.assignment2.service.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nagarro.java.mini.assignment2.entity.UserResponse;

@Component
public class NameSortingStrategy implements UserSortingStrategy {
    @Override
    public void sort(List<UserResponse> users, String sortingType) {
        Comparator<UserResponse> nameLengthComparator = Comparator.comparingInt(user -> user.getName().length());

        List<UserResponse> sortedUsers = users.stream()
                .sorted(nameLengthComparator)
                .collect(Collectors.toList());

        List<UserResponse> filteredUsers = sortingType.equalsIgnoreCase("ODD") ?
                filterOddLength(sortedUsers) : filterEvenLength(sortedUsers);

        users.clear();
        users.addAll(filteredUsers);
    }

    private List<UserResponse> filterOddLength(List<UserResponse> users) {
        return users.stream()
                .filter(user -> user.getName().length() % 2 != 0)
                .collect(Collectors.toList());
    }

    private List<UserResponse> filterEvenLength(List<UserResponse> users) {
        return users.stream()
                .filter(user -> user.getName().length() % 2 == 0)
                .collect(Collectors.toList());
    }
}
