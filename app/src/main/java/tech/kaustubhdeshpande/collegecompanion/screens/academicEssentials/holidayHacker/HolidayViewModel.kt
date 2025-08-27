package tech.kaustubhdeshpande.collegecompanion.screens.academicEssentials.holidayHacker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
class HolidayViewModel : ViewModel() {
    private val _holidays = MutableStateFlow(maharashtraHolidays2025)
    val holidays: StateFlow<List<Holiday>> = _holidays.asStateFlow()

    private val _breakSuggestions = MutableStateFlow<Map<Month, List<BreakSuggestion>>>(emptyMap())
    val breakSuggestions: StateFlow<Map<Month, List<BreakSuggestion>>> = _breakSuggestions.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            holidays.collectLatest { holidayList ->
                _breakSuggestions.value = findAllPossibleBreaksByMonth(holidayList)
            }
        }
    }
}