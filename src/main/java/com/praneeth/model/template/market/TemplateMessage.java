package com.praneeth.model.template.market;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type TemplateResponseDto message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateMessage {
  private String messaging_product;
  private String recipient_type;
  private String to;
  private String type;
  private Template template;

  /**
   * The type TemplateResponseDto.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Template {
    private String name;
    private Language language;
    private Component[] components;

    /**
     * The type Language.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Language {
      private String code;
    }

    /**
     * The type Component.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component {
      private String type;
      private Parameter[] parameters;

      /**
       * The type Parameter.
       */
      @Data
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Parameter {
        private String type;
        private String text;
        private Currency currency;
        private Date_time date_time;

        /**
         * The type Currency.
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Currency {
          private String fallback_value;
          private String code;
          private int amount_1000;
        }

        /**
         * The type Date time.
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Date_time {
          private String fallback_value;
        }
      }
    }
  }
}
