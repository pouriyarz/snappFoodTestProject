package com.rezaie.domain.domainCore

/**
 * A generic class for hiding/showing some ui component, such as dialogs.
 */
sealed class UIComponentState {

    data object Show: UIComponentState()

    data object Hide: UIComponentState()
}
