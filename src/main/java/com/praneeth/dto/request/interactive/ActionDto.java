package com.praneeth.dto.request.interactive;

import com.praneeth.dto.request.interactive.button.ButtonDto;
import com.praneeth.dto.request.interactive.list.SectionDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Action dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionDto {
  private ButtonDto[] buttons;
  private String button;
  private List<SectionDto> sections;
}