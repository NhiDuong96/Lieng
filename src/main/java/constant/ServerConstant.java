package constant;

import bitzero.server.config.ConfigHandle;

public class ServerConstant extends bitzero.framework.constant.ServerConstant {
    /*
     * Log scribed
     */

    public static final String KPI_SCRIBE_IP = ConfigHandle.instance().get("kpi_scribe_ip");
    public static final int KPI_SCRIBE_PORT = ConfigHandle.instance().getLong("kpi_scribe_port").intValue();

    public static final String GAME_SCRIBE_IP = ConfigHandle.instance().get("game_scribe_ip");
    public static final int GAME_SCRIBE_PORT = ConfigHandle.instance().getLong("game_scribe_port").intValue();

    public static final String IFRS_SCRIBE_IP = ConfigHandle.instance().get("ifrs_scribe_ip");
    public static final int IFRS_SCRIBE_PORT = ConfigHandle.instance().getLong("ifrs_scribe_port").intValue();

    public static final int SCRIBE_TIMEOUT = ConfigHandle.instance().getLong("scribe_timeout").intValue();

    public static final boolean ENABLE_LOCAL_LOG = ConfigHandle.instance().getLong("enable_local_log").intValue() == 1;
    public static final String LOCAL_LOG_PATH = ConfigHandle.instance().get("local_log_path");
    public static final String LOCAL_CCU_LOG_PATH = ConfigHandle.instance().get("local_ccu_log_path");

    public static final int MAX_USERS = ConfigHandle.instance().getLong("max_users").intValue();
    public static final int MAX_ROOMS = ConfigHandle.instance().getLong("max_rooms").intValue();
}
