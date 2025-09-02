CREATE TABLE token_support_request (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    uid BIGINT NOT NULL COMMENT '用户ID',
    token_name VARCHAR(20) NOT NULL COMMENT 'Token名称',
    token_symbol VARCHAR(20) NOT NULL COMMENT 'Token符号',
    token_type VARCHAR(20) NOT NULL COMMENT 'Token类型(bep20/erc20/mainchain)',
    contract_address VARCHAR(42) NOT NULL COMMENT '合约地址',
    decimals INT NOT NULL COMMENT '小数位数',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0:待审核,1:审核通过,-1:审核拒绝)',
    fee DECIMAL(20,8) NOT NULL DEFAULT 300 COMMENT '申请费用(USDT)',
    remark VARCHAR(255) DEFAULT NULL COMMENT '备注信息',
    reviewer_id BIGINT DEFAULT NULL COMMENT '审核人ID',
    review_time DATETIME DEFAULT NULL COMMENT '审核时间',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_uid (uid),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Token支持申请记录表';