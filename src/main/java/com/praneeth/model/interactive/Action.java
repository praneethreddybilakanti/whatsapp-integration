package com.praneeth.model.interactive;

import com.praneeth.model.interactive.button.Button;
import com.praneeth.model.interactive.list.Section;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type Action.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {
  /**
   * The Buttons.
   */
  private Button[] buttons;
  private String button;
  private List<Section> sections;
}