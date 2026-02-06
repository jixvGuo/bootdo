package com.bootdo.cpe.domain;

import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;

import java.util.ArrayList;
import java.util.List;

import static com.bootdo.common.utils.PoiWordUtils.getTableRenderData;

/**
 * 表格中对象数据列表
 */
public class PetroleumEngineAwardDocTableData {

    private List<OilAwardGetInfoDO> oilAwardGetInfoDOS;//获奖信息

    private List<OilAwardUnitInfoDO> oilAwardUnitInfoDOList;//单位信息

    private List<OilAwardPartakeUnitDO> oilAwardPartakeUnitDOS;//参见单位表

    public List<OilAwardGetInfoDO> getOilAwardGetInfoDOS() {
        return oilAwardGetInfoDOS;
    }

    public void setOilAwardGetInfoDOS(List<OilAwardGetInfoDO> oilAwardGetInfoDOS) {
        this.oilAwardGetInfoDOS = oilAwardGetInfoDOS;
    }

    public List<OilAwardUnitInfoDO> getOilAwardUnitInfoDOList() {
        return oilAwardUnitInfoDOList;
    }

    public void setOilAwardUnitInfoDOList(List<OilAwardUnitInfoDO> oilAwardUnitInfoDOList) {
        this.oilAwardUnitInfoDOList = oilAwardUnitInfoDOList;
    }

    public List<OilAwardPartakeUnitDO> getOilAwardPartakeUnitDOS() {
        return oilAwardPartakeUnitDOS;
    }

    public void setOilAwardPartakeUnitDOS(List<OilAwardPartakeUnitDO> oilAwardPartakeUnitDOS) {
        this.oilAwardPartakeUnitDOS = oilAwardPartakeUnitDOS;
    }
}
