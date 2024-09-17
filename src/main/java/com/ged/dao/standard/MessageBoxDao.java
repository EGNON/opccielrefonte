package com.ged.dao.standard;

import com.ged.entity.standard.MessageBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageBoxDao extends JpaRepository<MessageBox,Long> {
}
