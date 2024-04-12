package com.emp.modal;

import lombok.Data;

@Data
public class EmpFilterVo {
    private String searchBy; //columns
    private String searchKey;//search value
    private String searchType;//equal, less than, in, not in
}
