package com.bootcamp.induk.domain;

import lombok.*;

import java.util.Date;
import java.util.Objects;

//@Getter	// @Data를 넣기엔 Setter도 들어가서 협업 시 Setter 사용을 막아야 함.
//@ToString
//@EqualsAndHashCode

// @Setter(AccessLevel.NONE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date birth;
	private Date reg_date;
}
