package com.demo.springjwt.demo.Config;

import java.util.ArrayList;
import java.util.List;

import com.demo.springjwt.demo.dto.StudentDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewOuput {
	private long page;
	private List<StudentDTO> listResult= new ArrayList<>();

}
