package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.entity.opcciel.Charge;
import com.ged.projection.ChargeProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ChargeMapper {
    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;

    public ChargeMapper(OpcvmMapper opcvmMapper, NatureOperationMapper natureOperationMapper) {
        this.opcvmMapper = opcvmMapper;
        this.natureOperationMapper = natureOperationMapper;
    }

    public ChargeDto deCharge(Charge charge)
    {
        ChargeDto chargeDto = new ChargeDto();
        BeanUtils.copyProperties(charge, chargeDto);
        if(charge.getOpcvm()!=null) {
            chargeDto.setOpcvm(opcvmMapper.deOpcvm(charge.getOpcvm()));
        }

        if(charge.getNatureOperation()!=null) {
            chargeDto.setNatureOperation(natureOperationMapper.deNatureOperation(charge.getNatureOperation()));
        }
        return chargeDto;
    }

    public Charge deChargeDto(ChargeDto chargeDTO)
    {
        Charge charge = new Charge();
        BeanUtils.copyProperties(chargeDTO, charge);
        return charge;
    }
    public ChargeDto deChargeProjection(ChargeProjection chargeProjection)
    {
        if(chargeProjection == null)
            return null;
        ChargeDto ChargeDto = new ChargeDto();
        BeanUtils.copyProperties(chargeProjection, ChargeDto);

        if(chargeProjection.getNatureOperation() != null) {
            ChargeDto.setNatureOperation(natureOperationMapper.deNatureOperation(chargeProjection.getNatureOperation()));
        }

        if(chargeProjection.getOpcvm()!=null) {
            ChargeDto.setOpcvm(opcvmMapper.deOpcvm(chargeProjection.getOpcvm()));
        }
        return ChargeDto;
    }
}
