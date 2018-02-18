0<%@include file="/libs/granite/ui/global.jsp" %><%
%><%@page session="false"
          import="org.apache.commons.lang.StringUtils,
                  com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Field,
                  com.adobe.granite.ui.components.Tag,
					javax.jcr.Node"%><%

    Config cfg = cmp.getConfig();
    ValueMap vm = (ValueMap) request.getAttribute(Field.class.getName());
    Field field = new Field(cfg);
	String name = cfg.get("name", String.class);
    String value = vm.get("value", String.class);
      if (value == null) {
          value = cfg.get("defaultValue", "0");
      }

	Double min = cfg.get("min", Double.class);
	Double max = cfg.get("max", Double.class);
	Double step = cfg.get("step", Double.class);


%><coral-slider class="coral-Form-field custom-range-slider" name="<%= name %>" step="<%= step %>" min="<%= min %>" max="<%= max %>" value="<%= value %>" >

</coral-slider>
