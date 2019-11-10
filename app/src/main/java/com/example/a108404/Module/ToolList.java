package com.example.a108404.Module;

public class ToolList{
    Boolean using;
    String toolName;

    public ToolList(Boolean using, String toolName){
        this.using = using;
        this.toolName = toolName;
    }

    public Boolean getUsing() {
        return using;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setUsing(Boolean using) {
        this.using = using;
    }
}
