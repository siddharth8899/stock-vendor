package com.internship.stock.enitity;

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
