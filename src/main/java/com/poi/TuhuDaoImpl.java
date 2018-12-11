package com.poi;

import com.poi.utils.JdbcTemplateUtils;
import org.apache.commons.lang3.ArrayUtils;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public class TuhuDaoImpl {
  private JdbcTemplate jdbcTemplate;

  public void insertOrUpdate(List<List<String>> listList){
    /*for(Map.Entry<String,String> entry : map.entrySet()){
      System.out.println(entry.getKey() + "    " + entry.getValue());
    }*/
    jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();
    for(List<String> list : listList){
      //System.out.println(list);
      list.get(2).replace("/","\\/");
      String sql = "insert into t_tuhu (keyword,designation,illustrate,deep_link,landing_page,icon) values(?,?,?,?,?,?)";
      //jdbcTemplate.update(sql,ArrayUtils.addAll(list.toArray(),list.toArray()));
      jdbcTemplate.update(sql,list.toArray());
    }
    //insert ignore into t_qichacha (source_id,url,title,company_name,phone,mail,address,legal_representative,
    // registered_capital,contributed_capital,management_forms,establish_date,credit_code,taxpayer_code,registration_code,
    // organization_code,company_type,industry_involved,approval_date,registration_authority,area_name,english_name,old_name,
    // insured_number,staff_size,business_term,business_scope) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
    // on duplicate key update source_id=?,url=?,title=?,company_name=?,phone=?,mail=?,address=?,legal_representative=?,
    // registered_capital=?,contributed_capital=?,management_forms=?,establish_date=?,credit_code=?,taxpayer_code=?,registration_code=?
    // ,organization_code=?,company_type=?,industry_involved=?,approval_date=?,registration_authority=?,area_name=?,english_name=?,
    // old_name=?,insured_number=?,staff_size=?,business_term=?,business_scope=?





  }


}
