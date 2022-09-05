package com.example.interviewperson.person.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Peyman Mahdikhani on 9/5/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticalAdvice implements Serializable {
    private Integer identifier;
    private String message;
}
