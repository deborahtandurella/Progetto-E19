package entityComponent;

import entityComponent.components.GraphicComponent;
import entityComponent.components.LogicComponent;

public abstract class Entity {
    private GraphicComponent graphicComponent;
    private LogicComponent logicComponent;

    public Entity(LogicComponent logicComponent,GraphicComponent graphicComponent) {
        this.graphicComponent = graphicComponent;
        this.logicComponent = logicComponent;
    }

    public GraphicComponent getGraphicComponent() {
        return graphicComponent;
    }

    public void setGraphicComponent(GraphicComponent graphicComponent) {
        this.graphicComponent = graphicComponent;
    }

    public LogicComponent getLogicComponent() {
        return logicComponent;
    }

    public void setLogicComponent(LogicComponent logicComponent) {
        this.logicComponent = logicComponent;
    }
    public void update(int delta){
        if (logicComponent != null) {
            logicComponent.update(delta);
        }
    }
    public void render(){
        if (graphicComponent != null) {
            graphicComponent.render();
        }
    }
}
