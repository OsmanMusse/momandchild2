package datePicker

import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver

/**
 * A state object that can be hoisted to observe the date picker state. See
 * [rememberDatePickerState].
 *
 * The state's [selectedDateMillis] will provide a timestamp that represents the _start_ of the day.
 *
 * @param initialSelectedDateMillis timestamp in _UTC_ milliseconds from the epoch that
 * represents an initial selection of a date. Provide a `null` to indicate no selection. Note
 * that the state's
 * [selectedDateMillis] will provide a timestamp that represents the _start_ of the day, which
 * may be different than the provided initialSelectedDateMillis.
 * @param initialDisplayedMonthMillis timestamp in _UTC_ milliseconds from the epoch that
 * represents an initial selection of a month to be displayed to the user. In case `null` is
 * provided, the displayed month would be the current one.
 * @param yearRange an [IntRange] that holds the year range that the date picker will be limited
 * to
 * @param initialMaterialDisplayMode an initial [DisplayMode] that this state will hold
 * @see rememberDatePickerState
 * @throws [IllegalArgumentException] if the initial selected date or displayed month represent
 * a year that is out of the year range.
 */
@Stable
actual class AdaptiveDatePickerState @OptIn(ExperimentalMaterial3Api::class)
actual constructor(
    initialSelectedDateMillis: Long?,
    initialDisplayedMonthMillis: Long?,
    yearRange: IntRange,
    initialMaterialDisplayMode: DisplayMode,
    initialUIKitDisplayMode: UIKitDisplayMode
) {
    /**
     * A timestamp that represents the _start_ of the day of the selected date in _UTC_ milliseconds
     * from the epoch.
     *
     * In case no date was selected or provided, the state will hold a `null` value.
     *
     * @see [setSelection]
     */
    actual val selectedDateMillis: Long?
        get() = TODO("Not yet implemented")

    /**
     * Sets the selected date.
     *
     * @param dateMillis timestamp in _UTC_ milliseconds from the epoch that represents the date
     * selection, or `null` to indicate no selection.
     *
     * @throws IllegalArgumentException in case the given timestamps do not fall within the year
     * range this state was created with.
     */
    actual fun setSelection(dateMillis: Long?) {
    }

    /**
     * A mutable state of [DisplayMode] that represents the current display mode of the UI
     * (i.e. picker or input).
     */
    @OptIn(ExperimentalMaterial3Api::class)
    actual var displayMode: DisplayMode
        get() = TODO("Not yet implemented")
        set(value) {}

    actual companion object {
        /**
         * The default [Saver] implementation for [AdaptiveDatePickerState].
         */
        actual fun Saver(): Saver<AdaptiveDatePickerState, *> {
            TODO("Not yet implemented")
        }
    }

}