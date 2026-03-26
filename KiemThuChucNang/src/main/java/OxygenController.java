public class OxygenController {

    private static final double MIN_HR = 40.0;
    private static final double MAX_HR = 180.0;
    private static final double MIN_SPO2 = 70.0;
    private static final double MAX_SPO2 = 100.0;

    private static final double SAFE_SPO2_THRESHOLD = 94.0;
    private static final double BASE_OXYGEN_FLOW = 2.0;

    /**
     * Tính toán lưu lượng Oxy cần thiết.
     * * @param hr   Nhịp tim (nhịp/phút)
     * @param spo2 Nồng độ bão hòa Oxy (%)
     * @return Lưu lượng Oxy (L/min)
     * @throws IllegalArgumentException nếu thông số nằm ngoài dải an toàn
     */
    public static double calculateOxygenFlow(double hr, double spo2) throws IllegalArgumentException {
        if (hr < MIN_HR || hr > MAX_HR) {
            throw new IllegalArgumentException("Nhịp tim ngoài dải an toàn [40-180]: " + hr);
        }
        if (spo2 < MIN_SPO2 || spo2 > MAX_SPO2) {
            throw new IllegalArgumentException("Nồng độ SpO2 ngoài dải cho phép [70-100]: " + spo2);
        }

        if (spo2 >= SAFE_SPO2_THRESHOLD) {
            return BASE_OXYGEN_FLOW;
        } else {
            double flow = (100.0 - spo2) * (hr / 100.0);

            return Math.round(flow * 100.0) / 100.0;
        }
    }
}