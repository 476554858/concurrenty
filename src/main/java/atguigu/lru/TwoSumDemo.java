package atguigu.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数合
 */
public class TwoSumDemo {

    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            int partnerNum = target - nums[i];
            if(map.containsKey(partnerNum)){
                return new int[]{map.get(partnerNum), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] myIndex = twoSum(nums, target);
    }
}
