package com.usr.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserVo {
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date doj;
    private List<PhoneVo> phones;

}
