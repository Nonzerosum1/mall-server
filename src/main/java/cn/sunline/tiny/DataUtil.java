package cn.sunline.tiny;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("dataUtil")
public class DataUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DataUtil.class);

    private static final Map<String, String> loginUsersToken = new ConcurrentHashMap<String, String>();
    private static final Map<String, String> registingToken = new ConcurrentHashMap<String, String>();

    public String getUUID() {
	return UUID.randomUUID().toString();
    }

    public Date simpleDateParse(String dateStr) {
	try {
	    return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
	} catch (ParseException e) {
	    throw new RuntimeException(e.getMessage());
	}
    }
    
    private final int LENGTH = 6;

    /**
     * 这是典型的随机洗牌算法。 流程是从备选数组中选择一个放入目标数组中，
     * 将选取的数组从备选数组移除（放至最后，并缩小选择区域） 算法时间复杂度O(n)
     * 
     * @return 随机8为不重复数组
     */
    public String generateNumber() {
	String no = "";
	int[] defaultNums = new int[10];
	for (int i = 0; i < defaultNums.length; i++) {
	    defaultNums[i] = i;
	}

	Random random = new Random();
	int[] nums = new int[LENGTH];
	int canBeUsed = 10;
	for (int i = 0; i < nums.length; i++) { 
	    int index = random.nextInt(canBeUsed);
	    nums[i] = defaultNums[index];
	    swap(index, canBeUsed - 1, defaultNums);
	    canBeUsed--;
	}
	if (nums.length > 0) {
	    for (int i = 0; i < nums.length; i++) {
		no += nums[i];
	    }
	}
	return no;
    }

    private void swap(int i, int j, int[] nums) {
	int temp = nums[i];
	nums[i] = nums[j];
	nums[j] = temp;
    }

    public String generateNumber2() {
	String no = "";
	int num[] = new int[8];
	int c = 0;
	for (int i = 0; i < 8; i++) {
	    num[i] = new Random().nextInt(10);
	    c = num[i];
	    for (int j = 0; j < i; j++) {
		if (num[j] == c) {
		    i--;
		    break;
		}
	    }
	}
	if (num.length > 0) {
	    for (int i = 0; i < num.length; i++) {
		no += num[i];
	    }
	}
	return no;
    }

}
