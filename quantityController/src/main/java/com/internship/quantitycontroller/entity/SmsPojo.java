package com.internship.quantitycontroller.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SmsPojo {
    private String to;
    private String message;
}
