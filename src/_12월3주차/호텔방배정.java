package _12월3주차;

import java.util.HashMap;
import java.util.Map;

public class 호텔방배정 {
    static Map<Long, Long> reservation;
    static long K;

    public long[] solution(long k, long[] room_number) {
        reservation = new HashMap<>();
        K = k;
        long[] answer = new long[room_number.length];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = findBookableRoom(room_number[i]);
        }

        return answer;
    }

    private static long findBookableRoom(long room) {
        if (!reservation.containsKey(room)) {
            long nextRoom = (room != K) ? room + 1 : 1;
            reservation.put(room, nextRoom);
            return room;
        }
        long nextRoom = reservation.get(room);
        long bookableRoom = findBookableRoom(nextRoom);
        reservation.put(room, bookableRoom);
        return bookableRoom;
    }

    /*

    ### 아래 방식으로 했을 때 테케 3,4,5,6 시간 초과! 뭐가 달라서..? ###

    public long[] solution(long k, long[] room_number) {
        reservation = new HashMap<>();
        K = k;
        long[] answer = new long[room_number.length];

        int idx = 0;

        for (long room : room_number) {
            if (!reservation.containsKey(room)) {
                long nextRoom = (room != k) ? room + 1 : 1;

                reservation.put(room, nextRoom);
                answer[idx++] = room;
                continue;
            }
            long nextRoom = reservation.get(room);

            while (reservation.containsKey(nextRoom)) {
                nextRoom = reservation.get(nextRoom);
            }
            reservation.put(room, nextRoom);
            reservation.put(nextRoom, nextRoom + 1);
            answer[idx++] = nextRoom;
        }

        return answer;
    }
     */
}