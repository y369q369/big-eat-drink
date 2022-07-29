package com.demo.dto;

import com.demo.entity.Catalog;
import com.demo.entity.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class MenuDto extends Menu {

    private String catalogName;

}
