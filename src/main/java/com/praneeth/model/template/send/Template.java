package com.praneeth.model.template.send;

import lombok.Data;

/**
 * The type Template.
 */
@Data
public class Template {
  private String name;
  private Language language;
  private Component[] components;
}
