package dev.jamesroberts.timecube.data.storage

/**
 * Singleton cache for the current state of the list of alarms.
 *
 * The db is the source of truth for alarm state but we don't want to be constantly
 * reading & writing to the db, so most changes to the list of alarms are made here
 * and synced later.
 */
class AlarmCache {
}