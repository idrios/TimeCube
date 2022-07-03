package dev.jamesroberts.timecube.data.model

/**
 * Data representation of an alarm.
 * TODO: Should this implement ViewModel()?
 * TODO: This needs to be LiveData so it can be observable
 * TODO: Should be serializable so it can be saved
 */
data class Alarm(var time:String,            // DateTime
            var frequencyType:String,   // Enum - Daily vs SelectDays
            var recurDuration:String,   // DateTime - time between repeats
            var weekdays:String,        // DateTime
            var occurType:String,       // Enum - Unlimited vs limited
            var occurTotal:Int,         // Int - Total number of alarm occurrences
            var occurRemain:Int,        // Int - Occurrences remaining - turn alarm off at 0
            var sound:String,           // Audio - Sound to make
            val register:(alarm: Alarm)->Int // Method for registering this alarm into the db. Returns ID
) {
    private var id:Int = -1;            // Unique ID -- invalid until it is registered in db
    private var enabled:Boolean = true; // On by default when created

    fun main(){
        id = register(this);
    }
}