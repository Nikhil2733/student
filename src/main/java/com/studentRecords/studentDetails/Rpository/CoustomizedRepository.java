package com.studentRecords.studentDetails.Rpository;

import java.util.List;

public interface CoustomizedRepository {
    List<Object[]> findPagedResultForStudents(int startIndex, int maxlimit);
}
