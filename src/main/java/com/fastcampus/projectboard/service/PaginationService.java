package com.fastcampus.projectboard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 3;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages){
        int startNum = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNum = Math.min(startNum + BAR_LENGTH, totalPages);

        return IntStream.range(startNum, endNum).boxed().toList();
    }

    public int currentBarLength(){
        return BAR_LENGTH;
    }
}
