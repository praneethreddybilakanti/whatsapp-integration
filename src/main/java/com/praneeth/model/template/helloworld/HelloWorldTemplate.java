package com.praneeth.model.template.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Message template.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class HelloWorldTemplate {


  /**
   * The Name.
   */
  private String name;
  /**
   * The Language.
   */
  private Language language;


}
