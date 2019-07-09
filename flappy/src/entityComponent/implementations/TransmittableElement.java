package entityComponent.implementations;

import entityComponent.components.LogicComponent;

public interface TransmittableElement extends LogicComponent {
    SerializableElement getTransmittableVersion();
}
