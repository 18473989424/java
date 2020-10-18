import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EightQueen7 {
	private static final short K = 8; // 浣跨敤甯搁噺鏉ュ畾涔夛紝鏂逛究涔嬪悗瑙鐨囧悗闂
	private static short N = 0;

	public static void main(String[] args) throws Exception {
		for (N = 9; N <= 17; N++) {
			long count = 0;
			Date begin = new Date();
			/**
			 * 鍒濆鍖栨鐩橈紝浣跨敤涓�缁存暟缁勫瓨鏀炬鐩樹俊鎭� chess[n]=X:琛ㄧず绗琻琛孹鍒楁湁涓�涓殗鍚�
			 */

			List<short[]> chessList = new ArrayList<short[]>(N);
			for (short i = 0; i < N; i++) {
				short chess[] = new short[N];
				chess[0] = i;
				chessList.add(chess);
			}

			short taskSize = (short) (N / 2 + (N % 2 == 1 ? 1 : 0));
			// 鍒涘缓涓�涓嚎绋嬫睜
			ExecutorService pool = Executors.newFixedThreadPool(taskSize);
			// 鍒涘缓澶氫釜鏈夎繑鍥炲�肩殑浠诲姟
			List<Future<Long>> futureList = new ArrayList<Future<Long>>(taskSize);
			for (int i = 0; i < taskSize; i++) {
				Callable<Long> c = new EightQueenThread(chessList.get(i));
				// 鎵ц浠诲姟骞惰幏鍙朏uture瀵硅薄
				Future<Long> f = pool.submit(c);
				futureList.add(f);
			}
			// 鍏抽棴绾跨▼姹�
			pool.shutdown();

			for (short i = 0; i < (short) (taskSize - (N % 2 == 1 ? 1 : 0)); i++) {
				count += futureList.get(i).get();
			}
			count = count * 2;
			if (N % 2 == 1)
				count += futureList.get(N / 2).get();

			Date end = new Date();
			System.out.println("瑙ｅ喅 " + N + "鐨囧悗闂锛岀敤鏃讹細" + String.valueOf(end.getTime() - begin.getTime())
					+ "姣锛岃绠楃粨鏋滐細" + count);
		}
	}
}

class EightQueenThread implements Callable<Long> {
	private short[] chess;
	private short N;

	public EightQueenThread(short[] chess) {
		this.chess = chess;
		this.N = (short) chess.length;
	}

	public Long call() throws Exception {
		return putQueenAtRow(chess, (short) 1);
	}

	private Long putQueenAtRow(short[] chess, short row) {
		if (row == N) {
			return (long) 1;
		}

		short[] chessTemp = chess.clone();
		long sum = 0;
		/**
		 * 鍚戣繖涓�琛岀殑姣忎竴涓綅缃皾璇曟帓鏀剧殗鍚� 鐒跺悗妫�娴嬬姸鎬侊紝濡傛灉瀹夊叏鍒欑户缁墽琛岄�掑綊鍑芥暟鎽嗘斁涓嬩竴琛岀殗鍚�
		 */
		for (short i = 0; i < N; i++) {
			// 鎽嗘斁杩欎竴琛岀殑鐨囧悗
			chessTemp[row] = i;

			if (isSafety(chessTemp, row, i)) {
				sum += putQueenAtRow(chessTemp, (short) (row + 1));
			}
		}

		return sum;
	}

	private static boolean isSafety(short[] chess, short row, short col) {
		// 鍒ゆ柇涓笂銆佸乏涓娿�佸彸涓婃槸鍚﹀畨鍏�
		short step = 1;
		for (short i = (short) (row - 1); i >= 0; i--) {
			if (chess[i] == col) // 涓笂
				return false;
			if (chess[i] == col - step) // 宸︿笂
				return false;
			if (chess[i] == col + step) // 鍙充笂
				return false;

			step++;
		}

		return true;
	}
}