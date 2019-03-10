package z.learn.baseline.model;

import java.util.Map;

/**
 * DB里存储的数据结构
 *
 * 创建出来直接就是当前基线
 *
 * post   /components/{componentId}/baselines               创建当前基线。不需要创建出来不是当前基线的基线了。
 * get    /components/{componentId}/baselines?regionId=a&zoneId=b&schema=c.. 查询组件的基线 是否限制当前基线？
 * put    /components/{componentId}/baselines/{baselineId}  当前基线打标
 * delete /components/{componentId}/baselines/{baselineId}  删除当前极限
 *
 * get    /components/baselines                             基线批量查询结构
 *
 * post   /components/{componentId}/locks                   锁可以做系统间变更互斥的控制
 * get    /components/{componentId}/locks
 * put    /components/{componentId}/locks
 * delete /components/{componentId}/locks
 *
 * OS基线，收编到上面的基线
 * 插件基线
 * 公共配置，云服务配置，服务配置
 */
public class Baseline {
    public enum releaseCategoryEnum {
        production,
        gray
    }

    private Long baselineId;        //唯一标示
    private Integer componentId;    //required 有region概念的时候有id就可以了
    private String regionId;        //required 当前业务树模型虽然不必要，之后改造成没有region的业务树时候必填了
    private String zoneId;
    private String schema;          //required
    private String releaseCategory; //required

    private String componentName;   //展示类信息。服务实例和业务树都能拿到 只能做展示字段 名字可能会变化
    private String regionName;      //展示类信息。名字可能被修改

    private boolean using;          //当前正在使用的基线。不是正在使用的基线和有问题的基线都是回滚时候可以使用的基线
    private boolean faulted;        //有问题基线

    private String componentVersion;
    private String componentPkg;
    private String vars;

    private transient Map<String, Object> appEnv;
    private String appEnvironment;
    private transient Map<String, Object> runningEnv;//运行环境，需要考虑数据的继承。通过解析成bean，根据上面的注解来决定是否要继承数据。
    private String runningEnvironment;
    private transient Map<String, Object> vmEnv;//兼容原来的OS基线内容。设置之后不允许变化
    private String vmEnvironment;
    private transient Map<String, Object> baseEnv;
    private String baseEnvironment;
}
