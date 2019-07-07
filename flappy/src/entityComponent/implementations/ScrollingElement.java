package entityComponent.implementations;

import entityComponent.components.LogicComponent;

public interface ScrollingElement extends LogicComponent {
    boolean outOfBounds();
}
