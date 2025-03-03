package _3_3_25_RoomAllotment;

import java.util.HashMap;
import java.util.Map;
import java.util.EnumSet;

class RoomScheduler {
    Map<String, MeetingRoom> meetingRooms;

    public RoomScheduler() {
        this.meetingRooms = new HashMap<>();
    }

    public void addMeetingRoom(MeetingRoom room) {
        meetingRooms.put(room.roomId, room);
        System.out.println("Room added: " + room.roomName + ", ID: " + room.roomId);
    }

    public String bookRoom(String roomId, EnumSet<RoomFeature> requiredFeatures) {
        MeetingRoom room = meetingRooms.get(roomId);
        if (room != null && room.features.containsAll(requiredFeatures)) {
            return "Room " + roomId + " booked successfully.";
        } else {
            return "Room " + roomId + " does not meet the requirements.";
        }
    }

    public String listAvailableRooms(EnumSet<RoomFeature> requiredFeatures) {
        StringBuilder availableRooms = new StringBuilder();
        for (MeetingRoom room : meetingRooms.values()) {
            if (room.features.containsAll(requiredFeatures)) {
                availableRooms.append(room.roomName).append(", ");
            }
        }
        if (availableRooms.length() > 0) {
            return "Available rooms with " + requiredFeatures + ": [" +
                    availableRooms.substring(0, availableRooms.length() - 2) + "]";
        } else {
            return "No rooms available with " + requiredFeatures;
        }
    }
}

