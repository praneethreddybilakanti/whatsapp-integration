package com.praneeth.model.template.send;

import lombok.Data;

/**
 * The type Component.
 */
@Data
public class Component {
  private String type;
  private Parameter[] parameters;
}
